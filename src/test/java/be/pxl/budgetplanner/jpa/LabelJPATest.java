package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.data.Account;
import be.pxl.budgetplanner.data.Label;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class LabelJPATest {
    private LabelJPA jpa;

    @Before
    public void setUp() {
        jpa = new LabelJPA();
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
        assertThat(labels, CoreMatchers.hasItems(label));
    }

    @Test
    public void getLabelById_ReturnsTheCorrectLabel() {
        Label label = createLabel();
        Label foundLabel = jpa.getLabel(label.getId());
        assertThat(foundLabel, CoreMatchers.equalTo(label));
    }

    @Test
    public void removeLabel_RemovesTheLabel() {
        Label label = createLabel();
        jpa.removeLabel(label);
        Label foundLabel = jpa.getLabel(label.getId());
        assertThat(foundLabel, CoreMatchers.nullValue());
    }

    private Label createLabel() {
        Label label = new Label();
        label.setName("ACoolName");
        label.setDescription("An even cooler description");
        jpa.addLabel(label);
        return label;
    }
}
