package com.hjg.spring;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceApplication {

    public static void main(String[] args) throws IOException {
        String classpathLocation = "book/booklist.txt";
        Resource resource = new ClassPathResource(classpathLocation);

        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        String content = br.lines().collect(Collectors.joining());

        System.out.println("content=" + content);

        br.close();
    }
}
