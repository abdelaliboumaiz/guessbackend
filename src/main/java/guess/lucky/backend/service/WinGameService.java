package guess.lucky.backend.service;

import guess.lucky.backend.exceptions.ServiceException;
import guess.lucky.backend.models.WinGames;
import guess.lucky.backend.repository.WinGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class WinGameService {

    @Autowired
    private WinGameRepository winGameRepository;

    public WinGames winGameDayBack(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        if( winGameRepository.findByWinDate(date).isPresent()){
            return winGameRepository.findByWinDate(date).get();
        }
        throw new ServiceException("Table vide");
    }
}
