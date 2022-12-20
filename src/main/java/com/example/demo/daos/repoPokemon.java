package com.example.demo.daos;

import com.example.demo.modelos.PokemonListFormat;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class repoPokemon implements PokemonDao {
    @Override
    public List<PokemonListFormat> getAllPokemon() {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends PokemonListFormat> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<PokemonListFormat> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public PokemonListFormat getOne(Integer integer) {
        return null;
    }

    @Override
    public PokemonListFormat getById(Integer integer) {
        return null;
    }

    @Override
    public PokemonListFormat getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends PokemonListFormat> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends PokemonListFormat> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends PokemonListFormat, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> S save(S entity) {
        return null;
    }

    @Override
    public <S extends PokemonListFormat> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PokemonListFormat> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<PokemonListFormat> findAll() {
        return null;
    }

    @Override
    public List<PokemonListFormat> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(PokemonListFormat entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends PokemonListFormat> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<PokemonListFormat> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<PokemonListFormat> findAll(Pageable pageable) {
        return null;
    }
}
