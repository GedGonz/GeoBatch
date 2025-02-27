package com.geo.batch.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class KmzExtractTasklet implements Tasklet {


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        ClassPathResource resource = new  ClassPathResource("files/input/BIOMASA.kmz");
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(resource.getFile()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if(entry.getName().endsWith(".kml"))
                {
                    String path =resource.getFile().getParent();
                    File outputFile = new File(path.substring(0,path.length()-5), "output/BIOMASA.kml");
                    if (!outputFile.getParentFile().exists()) outputFile.getParentFile().mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }

                zis.closeEntry();
            }
        }
        return RepeatStatus.FINISHED;
    }
}