package guess.lucky.backend.api;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.News;
import guess.lucky.backend.models.NewsRequest;
import guess.lucky.backend.repository.NewsRepository;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/public")
public class NewsController {
    //private static final Logger log = LoggerFactory.getLogger(publicResources.class);
    private final Path root = Paths.get("uploads");

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping(value = "/news")
    public ResponseEntity<News> createNews(@ModelAttribute NewsRequest newsRequest){
        try {
            News news = new News();
            news.setCreationDate(new Date());
            news.setContent(newsRequest.getContent());
            news.setExpiredDate(newsRequest.getExpiredDate());
            news.setSubtitle(newsRequest.getSubtitle());
            news.setTitle(newsRequest.getSubtitle());
            news.setImageUrl(newsRequest.getImgUrl());
            return new ResponseEntity<>(newsRepository.save(news), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getNotExpiredNews(){
        return new ResponseEntity<>(newsRepository.getNotExpiredNews(new Date()), HttpStatus.OK);
    }

    /*@GetMapping("/news/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }*/
}
