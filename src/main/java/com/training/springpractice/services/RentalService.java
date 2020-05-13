package com.training.springpractice.services;

import com.training.springpractice.errors.NotFoundException;
import com.training.springpractice.models.Member;
import com.training.springpractice.models.Movie;
import com.training.springpractice.models.Rental;
import com.training.springpractice.models.RentalBody;
import com.training.springpractice.repositories.MemberRepository;
import com.training.springpractice.repositories.MovieRepository;
import com.training.springpractice.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("rentalService")
public class RentalService {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MemberRepository memberRepository;

    private ModelMapper mapper;

    public RentalService() {}

    public RentalBody create (RentalBody body) throws RuntimeException {
        Optional<Movie> movie = movieRepository.findById(body.getMovie_id());
        Optional<Member> member = memberRepository.findById(body.getMember_id());
        if (movie.isPresent() && member.isPresent()) {
            Rental rental = mapper.map(body, Rental.class);
            Rental createdRental = repository.saveAndFlush(rental);
            return mapper.map(createdRental, RentalBody.class);
        }
        throw new NotFoundException("Invalid information");
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

    private Rental convertToEntity(RentalBody body) {
        return mapper.map(body, Rental.class);
    }

    private RentalBody convertToDto(Rental rental) {
        return mapper.map(rental, RentalBody.class);
    }
}
