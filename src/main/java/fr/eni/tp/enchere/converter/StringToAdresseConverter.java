package fr.eni.tp.enchere.converter;

import fr.eni.tp.enchere.bll.EncheresService;
import fr.eni.tp.enchere.bo.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToAdresseConverter implements Converter<String, Adresse> {

    @Autowired
    private EncheresService encheresService;

    @Override
    public Adresse convert(String source) {
        long id = Long.parseLong(source);
        return encheresService.getAdresseById(id);
    }
}