package backend.mwvb.service;

import backend.mwvb.entity.Root;
import backend.mwvb.entity.Word;
import backend.mwvb.mapper.RootMapper;
import backend.mwvb.mapper.WordMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class BookServiceImpl implements BookService {
    private final RootMapper rootMapper;
    private final WordMapper wordMapper;

    @Override
    public List<Root> rootsInUnit(Integer index) {
        return rootMapper.rootsInUnit(index);
    }

    @Override
    public List<Word> wordsInRoot(Integer rootId) {
        return wordMapper.wordsInRoot(rootId);
    }
}