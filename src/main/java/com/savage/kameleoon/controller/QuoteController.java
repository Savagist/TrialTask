package com.savage.kameleoon.controller;

import com.savage.kameleoon.dto.QuoteDTO;
import com.savage.kameleoon.models.Quote;
import com.savage.kameleoon.services.QuoteService;
import com.savage.kameleoon.util.qoute.QuoteErrorResponse;
import com.savage.kameleoon.util.qoute.QuoteNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

    public final QuoteService quoteService;
    public final ModelMapper modelMapper;

    public QuoteController(QuoteService quoteService, ModelMapper modelMapper) {
        this.quoteService = quoteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/random")
    public QuoteDTO getRandomQuote() {
        return convertQuoteDTO(quoteService.findRandom());
    }

    @GetMapping("/{id}")
    public QuoteDTO getQuote(@PathVariable("id") Long id) {
        return convertQuoteDTO(quoteService.findOne(id));
    }

    @GetMapping
    public List<QuoteDTO> getQuotes() {
        return quoteService.findAll().stream().map(this::convertQuoteDTO).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuote(@PathVariable("id") Long id) {
        quoteService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> replace(@PathVariable("id") Long id, @RequestBody @Valid QuoteDTO quoteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMassage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMassage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new QuoteNotCreatedException(errorMassage.toString());
        }
        quoteService.update(id, convertToQuote(quoteDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid QuoteDTO quoteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMassage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMassage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new QuoteNotCreatedException(errorMassage.toString());
        }
        quoteService.save(convertToQuote(quoteDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/best")
    public List<QuoteDTO> bestQuotes() {
        return quoteService.bestQuotes().stream().map(this::convertQuoteDTO).collect(Collectors.toList());
    }

    @GetMapping("/worse")
    public List<QuoteDTO> worseQuotes() {
        return quoteService.worseQuotes().stream().map(this::convertQuoteDTO).collect(Collectors.toList());
    }

    @ExceptionHandler
    private ResponseEntity<QuoteErrorResponse> handleException() {
        QuoteErrorResponse response = new QuoteErrorResponse("Quote not found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<QuoteErrorResponse> handleException(QuoteNotCreatedException exception) {
        QuoteErrorResponse response = new QuoteErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Quote convertToQuote(QuoteDTO quoteDTO) {
        return modelMapper.map(quoteDTO, Quote.class);
    }

    private QuoteDTO convertQuoteDTO(Quote quote) {
        return modelMapper.map(quote, QuoteDTO.class);
    }
}




