package guess.lucky.backend.service;

import guess.lucky.backend.models.Game;
import guess.lucky.backend.models.WinGames;
import guess.lucky.backend.models.config.User;
import guess.lucky.backend.repository.GameHisotryRepository;
import guess.lucky.backend.repository.WinGameRepository;
import guess.lucky.backend.repository.config.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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
        Game winner = WinnerGame();
        WinGames winGames = new WinGames();
        winGames.setWinDate(new Date());
        winGames.setAmountWon(1000);
        winGames.setChosenHoroscopes(winner.getHoroscopes());
        winGames.setChosenNumbers(winner.getChosenNumbers());
        winGameRepository.save(winGames);
    }

    public Game WinnerGame() {
        AtomicInteger counter = new AtomicInteger();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        int size = gameHisotryRepository.gameCountByDay();
        int random = new Random().nextInt(size + 1);
        List<Game> games = gameHisotryRepository.findGameByGamePlayedAt(date);

        Game game = games.get(random);

        Game finalGame = game;
        List<Game> gameList = gameHisotryRepository.findGameByGamePlayedAt(date)
                .stream()
                .filter(game1 -> game1.getHoroscopes().equals(finalGame.getHoroscopes()) &&
                        game1.getChosenNumbers().equals(finalGame.getChosenNumbers()))
                .collect(Collectors.toList());

        while (gameList.size() != 1) {
            random = new Random().nextInt(size + 1);
            game = games.get(random);
            Game finalGame1 = game;
            gameList = gameHisotryRepository.findGameByGamePlayedAt(date)
                    .stream()
                    .filter(game1 -> game1.getHoroscopes().equals(finalGame1.getHoroscopes()) &&
                            game1.getChosenNumbers().equals(finalGame1.getChosenNumbers()))
                    .collect(Collectors.toList());
        }
        return game;
    }

    public void upgradeScore(){
        WinGames winGames = winGameRepository.findByWinDate(new Date()).get();
        String[] arrHoroscope = winGames.getChosenHoroscopes().split(",");
        String[] arrNumber = winGames.getChosenNumbers().split(",");
        userRepository.findAll().forEach(user -> {
            Set<String> numbers = new HashSet<>();
            Set<String> horoscopes = new HashSet<>();
            gameHisotryRepository.findByUserIdAndGamePlayedAt(user.getId(), new Date()).forEach(game -> {
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

    public void changeScore(String[] arrHoroscope, String[] arrNumber,User user, Set<String> numbers, Set<String> horoscopes){
        int counter = user.getScore();

        for(String s: arrHoroscope){
            String search = numbers.stream().filter(t -> t.compareTo(s) == 0)
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
        user.setScore(counter);
        userRepository.save(user);
    }

}
