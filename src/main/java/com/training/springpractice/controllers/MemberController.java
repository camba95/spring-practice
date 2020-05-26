package com.training.springpractice.controllers;

import com.training.springpractice.models.Member;
import com.training.springpractice.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberRepository repository;

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
        Member createdMember = repository.saveAndFlush(member);
        return ResponseEntity.ok(createdMember);
    }
}
