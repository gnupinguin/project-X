package ru.dins.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dins.kafka.consumer.LoggersMessageStore;
import ru.dins.kafka.producer.QuoteProducer;
import ru.dins.kafka.producer.UnsentQuoteException;
import ru.dins.web.persistence.QuoteRepository;
import ru.dins.web.model.quote.Quote;

import java.net.ConnectException;
import java.util.List;


/**
 * Created by gnupinguin on 19.02.17.
 */

@Controller
public class ServerController {

    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QuoteRepository repository;

    @Autowired
    private QuoteProducer producer;

    @Value("${server.remote-host}")
    private String remoteHost;

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/create")
    public String createQuote(){
        return "create-quote.html";
    }

    @RequestMapping(value="/add")
    public String addQuote(@RequestParam(value = "quote") String quoteText,
                           @RequestParam(value = "author") String author,
                           Model model){
        try{
            quoteText = quoteText.trim();
            author = author.trim();
            if (quoteText.equals("") || author.equals("")){
                model.addAttribute("message", LoggersMessageStore.ERROR_ADDING_QUOTE_MESSAGE);
                return "status";
            }
            producer.addQuote2LocalTopic(new Quote(author, quoteText));
            model.addAttribute("message", LoggersMessageStore.SUCCESS_ADDING_QUOTE_MESSAGE);
        } catch (UnsentQuoteException e){
            logger.error(e.getMessage());
            return String.format("redirect:%s/add?quote=%s&author=%s",remoteHost,quoteText,author);
        }
        return "status";
    }

    @RequestMapping(value = "/data")
    public String showQuotes(Model model){
        List<Quote> quotes = null;
        try{
            quotes = repository.findAll();
            model.addAttribute("quotes",  quotes);
            return "data";
        } catch (ConnectException e){
            logger.warn(LoggersMessageStore.WARNING_REPOSITORY_CONNECTION_FAILED);
            return "redirect:" + remoteHost + "/data";
        }

    }

}
