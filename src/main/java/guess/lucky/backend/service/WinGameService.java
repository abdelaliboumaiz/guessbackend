package guess.lucky.backend.service;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.WinGames;
import guess.lucky.backend.repository.WinGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class WinGameService {

    @Autowired
    private WinGameRepository winGameRepository;

    public WinGames winGameDayBack(){
        if( winGameRepository.findByWinDate(new Date()).isPresent()){
            return winGameRepository.findByWinDate(new Date()).get();
        }
        throw new ServiceException("");
    }
}
