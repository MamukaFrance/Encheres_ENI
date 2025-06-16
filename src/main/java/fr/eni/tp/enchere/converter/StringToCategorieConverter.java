package fr.eni.tp.enchere.converter;

import fr.eni.tp.enchere.bll.EncheresService;
import fr.eni.tp.enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {

    @Autowired
    private EncheresService encheresService;

    @Override
    public Categorie convert(String source) {
        long id = Long.parseLong(source);
        return encheresService.getCategoriesById(id);
    }
}