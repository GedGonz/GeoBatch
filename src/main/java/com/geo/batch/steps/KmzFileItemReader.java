package com.geo.batch.steps;

import com.geo.batch.model.Apoyos;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;


@Log4j2
@Component
@StepScope
public class KmzFileItemReader implements ItemReader<Apoyos> {
    private final Queue<Apoyos> places = new LinkedList<>();


    public KmzFileItemReader() {
        try {
            File kmlFile = new ClassPathResource("files/output/BIOMASA.kml").getFile(); // Archivo extraído del KMZ
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(kmlFile);
            document.getDocumentElement().normalize();

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expressionApoyos = xPath.compile("//Folder[name='APOYOS']/Placemark");
            XPathExpression expressionCoordinates = xPath.compile("Point/coordinates");

            NodeList apoyos =  (NodeList) expressionApoyos.evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < apoyos.getLength(); i++) {
                Element placemark = (Element) apoyos.item(i);
                String name = placemark.getElementsByTagName("name").item(0).getTextContent();
                NodeList coordinatesNodes = (NodeList) expressionCoordinates.evaluate(placemark, XPathConstants.NODESET);
                String []coordinates = IntStream.range(0, coordinatesNodes.getLength()).mapToObj(j ->  coordinatesNodes.item(j).getTextContent().trim().split(",")[0] +","+ coordinatesNodes.item(j).getTextContent().trim().split(",")[1]).toArray(String[]::new);

                if (name != null && !name.isBlank() && coordinates.length > 0) {
                    places.add(Apoyos.builder()
                            .name(name)
                            .coordinates(coordinates)
                            .build());
                } else {
                    log.warn("Se encontró un apoyo -> {} inválido y será descartado.",name);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Apoyos read() {

        Apoyos apoyo = places.poll();

        if (apoyo != null) log.info("Leyendo apoyos y coordenadas {}", apoyo.getName());

        return apoyo; // Retorna cada apoyo con sus coordenadas
    }


}
