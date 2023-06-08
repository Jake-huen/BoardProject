package jscode.service;

import jscode.repository.ArticleCommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommentsService {

    private final ArticleCommentsRepository articleCommentsRepository;

}
