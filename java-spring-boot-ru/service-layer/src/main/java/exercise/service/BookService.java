package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookMapper bookMapper;

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO get(Long id) {
        var mbModel = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        return bookMapper.map(mbModel);
    }

    public BookDTO create(BookCreateDTO createDTO) {
        var model = bookMapper.map(createDTO);
        return bookMapper.map(bookRepository.save(model));
    }

    public BookDTO update(BookUpdateDTO updateDTO, Long id) {
        var mbModel = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(updateDTO, mbModel);
        return bookMapper.map(bookRepository.save(mbModel));
    }

    public void delete(Long id) {
        var mbModel = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookRepository.delete(mbModel);
    }
    // END
}
