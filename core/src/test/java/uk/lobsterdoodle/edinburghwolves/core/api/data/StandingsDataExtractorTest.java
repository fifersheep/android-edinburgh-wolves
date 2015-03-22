package uk.lobsterdoodle.edinburghwolves.core.api.data;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.lobsterdoodle.edinburghwolves.core.api.model.Team;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StandingsDataExtractorTest {


//    @Test
//    public void the_teams_are_extracted_correctly() throws Exception {
//        final Document document = mock(Document.class);
//        final Elements documentElements = mock(Elements.class);
//        final Element tableElement = mock(Element.class);
//        final Elements rowElements = mock(Elements.class);
//        final List<Element> rowData = mockRowData(rowElements);
//        when(documentElements.first()).thenReturn(tableElement);
//        when(document.select("table[class=footable]")).thenReturn(documentElements);
//        when(tableElement.select("tr")).thenReturn(rowElements);
//        when(rowElements.subList(1, rowElements.size())).thenReturn(rowData);
//
//        final StandingsDataExtractor extractor = new StandingsDataExtractor(document);
//        final Team[] actual = extractor.getTeams();
//
//        final Team[] expected = anyFourTeams();
//        assertTrue(Arrays.equals(expected, actual));
//    }
//
//    private List<Element> mockRowData(Elements rows) {
//        List<Element> rowData = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            final Element row = mock(Element.class);
//            when(rows.indexOf(row)).thenReturn(0);
//            final Elements rowColumns = mock(Elements.class);
//            when(row.select("td")).thenReturn(rowColumns);
//            rowData.add(row);
//        }
//        return rowData;
//    }
//
//    private Team[] anyFourTeams() {
//        return new Team[]{
//                mock(Team.class),
//                mock(Team.class),
//                mock(Team.class),
//                mock(Team.class)
//        };
//    }
}