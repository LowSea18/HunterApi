package com.example.HunterApi.repository;

import com.example.HunterApi.model.Hunter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HunterRepository implements JpaRepository<Hunter, Long> {


    @Override
    public List<Hunter> findAll() {
        return null;
    }

    @Override
    public List<Hunter> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Hunter> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Hunter> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Hunter hunter) {

    }

    @Override
    public void deleteAll(Iterable<? extends Hunter> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Hunter> S save(S s) {
        return null;
    }

    @Override
    public <S extends Hunter> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Hunter> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Hunter> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Hunter> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Hunter getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Hunter> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Hunter> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Hunter> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Hunter> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Hunter> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Hunter> boolean exists(Example<S> example) {
        return false;
    }
}
