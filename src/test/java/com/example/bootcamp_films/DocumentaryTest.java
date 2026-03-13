package com.example.bootcamp_films;

import com.example.bootcamp_films.entity.Documentary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentaryTest {

    @Test
    void createDocumentarySuccessfully() {
        Documentary doc = new Documentary(
                "Planeta Terra",
                "Alastair Fothergill",
                2006,
                "Natureza",
                550,
                true
        );

        assertEquals("Planeta Terra", doc.getTitle());
        assertEquals("Alastair Fothergill", doc.getDirector());
        assertEquals(2006, doc.getReleaseYear());
        assertEquals("Natureza", doc.getGenre());
        assertEquals(550, doc.getDuration());
        assertTrue(doc.isNarrated());
    }

    @Test
    void setNarratedTrue() {
        Documentary doc = new Documentary(
                "Planeta Terra",
                "Alastair Fothergill",
                2006,
                "Natureza",
                550,
                false
        );

        doc.setNarrated(true);

        assertTrue(doc.isNarrated());
    }

    @Test
    void setNarratedFalse() {
        Documentary doc = new Documentary(
                "Planeta Terra",
                "Alastair Fothergill",
                2006,
                "Natureza",
                550,
                true
        );

        doc.setNarrated(false);

        assertFalse(doc.isNarrated());
    }

    @Test
    void inheritedSetTitleSuccessfully() {
        Documentary doc = new Documentary(
                "Planeta Terra",
                "Alastair Fothergill",
                2006,
                "Natureza",
                550,
                true
        );

        doc.setTitle("Planeta Terra II");

        assertEquals("Planeta Terra II", doc.getTitle());
    }

    @Test
    void inheritedSetTitleBlankShouldThrowException() {
        Documentary doc = new Documentary(
                "Planeta Terra",
                "Alastair Fothergill",
                2006,
                "Natureza",
                550,
                true
        );

        assertThrows(IllegalArgumentException.class, () ->
                doc.setTitle("")
        );
    }
}