package guess.lucky.backend.service;

import guess.lucky.backend.models.Game;
import guess.lucky.backend.models.WinGames;
import guess.lucky.backend.models.Winners;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.GameHisotryRepository;
import guess.lucky.backend.repository.WinGameRepository;
import guess.lucky.backend.repository.WinnersRepository;
import guess.lucky.backend.repository.config.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@EnableAsync
public class SchedulerTask {

    private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameHisotryRepository gameHisotryRepository;

    @Autowired
    private WinGameRepository winGameRepository;

    @Autowired
    private WinnersRepository winnersRepository;

    @Async
    @Scheduled(cron = "0 10 00 * * ?", zone = "Europe/Paris")
    public void resetTryPeerDay(){
        userRepository.findAll().forEach(user -> {
            user.setHeartPeerDay(5);
            userRepository.save(user);
            log.info("reset for user " + user.getId());
        });
    }

    @Async
    @Scheduled(cron = "0 10 00 * * ?", zone = "Europe/Paris")
    public void winnerGamePeerDay(){
        Game gameWin = WinnerGame();
        if(gameWin != null){
            WinGames winGames = new WinGames();
            winGames.setWinDate(new Date());
            winGames.setAmountWon(1000);
            winGames.setChosenHoroscopes(gameWin.getHoroscopes());
            winGames.setChosenNumbers(gameWin.getChosenNumbers());
            winGameRepository.save(winGames);

            Winners winners = new Winners();
            winners.setUser(gameWin.getUser());
            winners.setAmount(1000);
            winners.setWinDate(new Date());
            winners.setCheck(false);
            winnersRepository.save(winners);
        }else{
            log.info("No winner for today");
        }
    }

    public Game WinnerGame() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        List<Game> games = gameHisotryRepository.findGameByGamePlayedAt(date);
        int size = games.size();
        int random = new Random().nextInt(size);

        Game game = games.get(random);
        List<Game> winList = gameHisotryRepository.findGameWin(date, game.getChosenNumbers(), game.getHoroscopes());
        int count = 0;
        while (winList.size() != 1) {
            if(count < size){
                random = new Random().nextInt(size);
                game = games.get(random);
                winList = gameHisotryRepository.findGameWin(date, game.getChosenNumbers(), game.getHoroscopes());
            }else{
                break;
            }
            count++;
        }
        if(count != size)
            return game;
        return null;
    }

    @Async
    @Scheduled(cron = "0 30 00 * * ?", zone = "Europe/Paris")
    public void upgradeScore(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        WinGames winGames = winGameRepository.findByWinDate(new Date()).orElse(null);
        if(winGames != null){
            String[] arrHoroscope = winGames.getChosenHoroscopes().split(",");
            String[] arrNumber = winGames.getChosenNumbers().split(",");
            userRepository.findAll().forEach(user -> {
                Set<String> numbers = new HashSet<>();
                Set<String> horoscopes = new HashSet<>();
                gameHisotryRepository.findByUserIdAndGamePlayedAt(user.getId(), calendar.getTime()).forEach(game -> {
                    String[] arrHoros = game.getHoroscopes().split(",");
                    for(String s : arrHoros){
                        horoscopes.add(s);
                    }
                    String[] arrNum = game.getChosenNumbers().split(",");
                    for(String s : arrNum){
                        numbers.add(s);
                    }
                });
                changeScore(arrHoroscope, arrNumber, user, numbers, horoscopes);
            });
        }
    }

    public void changeScore(String[] arrHoroscope, String[] arrNumber,User user, Set<String> numbers, Set<String> horoscopes){
        int counter = user.getScore();
        for(String s: arrHoroscope){
            String search = horoscopes.stream().filter(t -> t.compareTo(s) == 0)
                    .findAny().orElse(null);
            if(search != null)
                counter+=10;
        }

        for(String s: arrNumber){
            String search = numbers.stream().filter(t -> t.compareTo(s) == 0)
                    .findAny().orElse(null);
            if(search != null)
                counter+=10;
        }
        log.info("User Id : " + user.getId() + " New score : " + counter);
        user.setScore(counter);
        userRepository.save(user);
    }

}
