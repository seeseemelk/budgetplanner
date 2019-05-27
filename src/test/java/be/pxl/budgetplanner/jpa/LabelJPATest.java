package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.beans.Label;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LabelJPATest {
    private LabelJPA jpa;

    @Before
    public void setUp() {
        jpa = new LabelJPA("budgetplanner-test");
    }

    @After
    public void tearDown() throws Exception {
        jpa.close();
    }

    @Test
    public void getLabels_ReturnsAllLabels() {
        List<Label> labels = jpa.getLabels();
        assertEquals("Labels list should be empty", 0, labels.size());
    }

    @Test
    public void addLabel_AddsALabel() {
        Label label = createLabel();
        List<Label> labels = jpa.getLabels();
        assertThat(labels, hasItems(label));
    }

    @Test
    public void getLabelById_ReturnsTheCorrectLabel() {
        Label label = createLabel();
        Label foundLabel = jpa.getLabel(label.getId());
        assertThat(foundLabel, equalTo(label));
    }

    @Test
    public void removeLabel_RemovesTheLabel() {
        Label label = createLabel();
        assertThat(jpa.removeLabel(label), equalTo(label));
        assertThat(jpa.getLabel(label.getId()), nullValue());
    }

    private Label createLabel() {
        Label label = new Label();
        label.setName("ACoolName");
        label.setDescription("An even cooler description");
        assertThat(jpa.addLabel(label), equalTo(label));
        return label;
    }
}
