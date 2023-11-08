package com.example.asm2.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static String CV_UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/cv";
    private static String IMAGES_UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/images";
    public static void writeCvFile (MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            Path fileNameAndPath = Paths.get(CV_UPLOAD_DIRECTORY, fileName);
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeImageFile (MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            Path fileNameAndPath = Paths.get(IMAGES_UPLOAD_DIRECTORY, fileName);
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCvFile(String fileName) {
        Path path = Paths.get(CV_UPLOAD_DIRECTORY, fileName);
        try {
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteImageFile(String fileName) {
        Path path = Paths.get(System.getProperty("user.dir"), fileName);
        try {
            Files.delete(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
