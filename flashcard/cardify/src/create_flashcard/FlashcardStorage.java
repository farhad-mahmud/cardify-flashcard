package create_flashcard;

import create_flashcard.Flashcard;

import java.util.*;

public class FlashcardStorage {
    private static final Map<String, List<Flashcard>> flashcardStore = new HashMap<>();

    public static void addCard(String subject, Flashcard card) {
        flashcardStore.putIfAbsent(subject, new ArrayList<>());
        flashcardStore.get(subject).add(card);
    }

    public static List<Flashcard> getCards(String subject) {
        return flashcardStore.getOrDefault(subject, new ArrayList<>());
    }

    public static boolean hasEnough(String subject, int min) {
        return getCards(subject).size() >= min;
    }

    public static Set<String> getAllSubjects() {
        return flashcardStore.keySet();
    }
}
