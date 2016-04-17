package com.tipico.social.marketing.repository;

import com.tipico.social.marketing.contract.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ncamilleri on 17/04/16.
 */
@Component("campaignsProvider")
public class CampaignsProvider implements CampaignsRepository {
    public <S extends Campaign> List<S> save(Iterable<S> iterable) {
        return null;
    }

    public <S extends Campaign> S save(S s) {
        return null;
    }

    public Campaign findOne(String s) {
        return null;
    }

    public boolean exists(String s) {
        return false;
    }

    public List<Campaign> findAll() {
        return null;
    }

    public Iterable<Campaign> findAll(Iterable<String> iterable) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void delete(String s) {

    }

    public void delete(Campaign campaign) {

    }

    public void delete(Iterable<? extends Campaign> iterable) {

    }

    public void deleteAll() {

    }

    public List<Campaign> findAll(Sort sort) {
        return null;
    }

    public Page<Campaign> findAll(Pageable pageable) {
        return null;
    }

    public <S extends Campaign> List<S> insert(Iterable<S> iterable) {
        return null;
    }

    public <S extends Campaign> S insert(S s) {
        return null;
    }
}
