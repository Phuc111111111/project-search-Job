package com.example.asm2.service;

import com.example.asm2.dao.CvRepository;
import com.example.asm2.entity.Cv;
import com.example.asm2.entity.User;
import com.example.asm2.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class CvServiceImpl implements CvService{
    private CvRepository cvRepository;

    @Autowired
    public CvServiceImpl(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Override
    @Transactional
    public void save(Cv cv) {
        cvRepository.save(cv);
    }

    @Override
    @Transactional
    public void deleteById(Integer cvId) {
        cvRepository.deleteById(cvId);
    }

    @Override
    @Transactional
    public Cv findById(Integer cvId) {
        Optional<Cv> result = cvRepository.findById(cvId);
        Cv cv = null;

        if (result.isPresent()) {
            cv = result.get();
        } else {
            throw new RuntimeException("Did not find cv id - " + cvId);
        }
        return cv;
    }

    @Override
    public Cv createCvForUser(MultipartFile file, User user) {
        String fileName = file.getOriginalFilename();  // have to handle case: fileName is the same with other user's nameCv
        Cv cv = null;
        if (user.getCv() == null) {
            cv = new Cv(fileName,user);
        } else {
            cv = user.getCv();
            FileUtils.deleteCvFile(cv.getFileName());
            cv.setFileName(fileName);
        }
        return cv;
    }
}
