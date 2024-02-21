package com.backend.velanmai.Validater;

import com.backend.velanmai.Common.Errors;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserArticleValidater {
    public List<Errors> validater(String article) {
        List<Errors>errors=new ArrayList<>();

        if (article.isEmpty()|article.isBlank())
        {
            errors.add(new Errors("content","it is empty"));
        }
        if (article.length()<200)
        {
            errors.add(new Errors("length","minimum 200 length"));
        }

        return errors;
    }
}
