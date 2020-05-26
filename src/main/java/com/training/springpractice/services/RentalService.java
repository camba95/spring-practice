package com.training.springpractice.services;

import com.training.springpractice.enums.RentalStatus;
import com.training.springpractice.errors.BadRequestException;
import com.training.springpractice.errors.NotFoundException;
import com.training.springpractice.models.*;
import com.training.springpractice.repositories.CatalogRepository;
import com.training.springpractice.repositories.MemberRepository;
import com.training.springpractice.repositories.MovieRepository;
import com.training.springpractice.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("rentalService")
public class RentalService {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    private ModelMapper mapper;

    public RentalService() {}

    @Transactional
    public RentalBody create(RentalBody body) throws RuntimeException {
        Optional<Movie> movie = movieRepository.findById(body.getMovie_id());
        Optional<Member> member = memberRepository.findById(body.getMember_id());
        if (movie.isPresent() && member.isPresent()) {
            validateCopies(movie.get());
            Rental rental = mapper.map(body, Rental.class);
            rental.setStatus(RentalStatus.RENTED);
            Rental createdRental = repository.saveAndFlush(rental);
            catalogRepository.decreaseCopies(movie.get().getId());
            return mapper.map(createdRental, RentalBody.class);
        }
        throw new NotFoundException("Invalid information");
    }

    @Transactional
    public RentalOnlyStatus updateStatus(Long id, RentalOnlyStatus status) {
        Optional<Rental> rental = repository.findById(id);
        if (status.getStatus() == RentalStatus.RETURNED && rental.isPresent()) {
            validateStatus(rental.get());
            Rental found = rental.get();
            BeanUtils.copyProperties(status, found);
            Rental updated = repository.saveAndFlush(found);
            catalogRepository.increaseCopies(rental.get().getMovie().getId());
            return mapper.map(updated, RentalOnlyStatus.class);
        }
        throw new BadRequestException("Invalid information");
    }

    @Autowired
    public void setMapper( ModelMapper mapper) {
        this.mapper = mapper;
        this.mapper.addMappings(new PropertyMap<RentalBody, Rental>() {
            @Override
            protected void configure() {
                map().setMovieId(source.getMovie_id());
                map().setMemberId(source.getMember_id());
            }
        });
        this.mapper.addMappings(new PropertyMap<Rental, RentalBody>() {
            @Override
            protected void configure() {
                map().setMovie_id(source.getMovie().getId());
                map().setMember_id(source.getMember().getId());
            }
        });
    }

    private void validateCopies(Movie movie) {
        Catalog catalog = catalogRepository.findByMovie(movie);
        if (catalog.getCopies() <= 0) {
            throw new BadRequestException("Movie not available");
        }
    }

    private void validateStatus(Rental rental) {
        if (rental.getStatus() != RentalStatus.RENTED) {
            throw new BadRequestException("Invalid rental information");
        }
    }

    private Rental convertToEntity(RentalBody body) {
        return mapper.map(body, Rental.class);
    }

    private RentalBody convertToDto(Rental rental) {
        return mapper.map(rental, RentalBody.class);
    }
}
