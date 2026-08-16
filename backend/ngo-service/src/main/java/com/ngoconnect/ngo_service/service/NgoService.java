package com.ngoconnect.ngo_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ngoconnect.ngo_service.entity.Ngo;
import com.ngoconnect.ngo_service.repository.NgoRepository;

@Service
public class NgoService {

    private final NgoRepository ngoRepository;

    public NgoService(NgoRepository ngoRepository) {
        this.ngoRepository = ngoRepository;
    }

    public Ngo createNgo(Ngo ngo) {

        if (ngoRepository.existsByEmail(ngo.getEmail())) {
            throw new RuntimeException("NGO email already registered");
        }

        if (ngoRepository.existsByRegistrationNumber(
                ngo.getRegistrationNumber())) {
            throw new RuntimeException(
                    "NGO registration number already exists");
        }

        return ngoRepository.save(ngo);
    }

    public List<Ngo> getAllNgos() {
        return ngoRepository.findAll();
    }

    public Ngo getNgoById(Long id) {

        return ngoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "NGO not found with id: " + id));
    }

    public Ngo updateNgo(Long id, Ngo updatedNgo) {

        Ngo existingNgo = getNgoById(id);

        existingNgo.setName(updatedNgo.getName());
        existingNgo.setEmail(updatedNgo.getEmail());
        existingNgo.setPhone(updatedNgo.getPhone());
        existingNgo.setAddress(updatedNgo.getAddress());
        existingNgo.setCity(updatedNgo.getCity());
        existingNgo.setState(updatedNgo.getState());
        existingNgo.setRegistrationNumber(
                updatedNgo.getRegistrationNumber());
        existingNgo.setVerified(updatedNgo.isVerified());

        return ngoRepository.save(existingNgo);
    }

    public void deleteNgo(Long id) {

        Ngo ngo = getNgoById(id);

        ngoRepository.delete(ngo);
    }
}