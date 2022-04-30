package backend.mwvb.controller;

import backend.mwvb.entity.Root;
import backend.mwvb.entity.Word;
import backend.mwvb.service.BookService;
import backend.mwvb.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService service) {
        bookService = service;
    }

    @GetMapping("/roots/{index}")
    public Response<List<Root>> rootsInUnit(@PathVariable("index") Integer index) {
        List<Root> roots = bookService.rootsInUnit(index);
        return Response.success(roots);
    }

    @PostMapping("/words")
    public Response<List<Word>> wordsInRoot(@RequestBody Root root) {
        // TODO: 2022/4/30 validate root
        List<Word> words = bookService.wordsInRoot(root.getId());
        return Response.success(words);
    }
}
