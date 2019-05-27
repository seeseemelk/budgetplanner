package be.pxl.budgetplanner.dao;

import be.pxl.budgetplanner.beans.Label;

import java.util.List;

public interface LabelDAO {
    List<Label> getLabels();
    Label getLabel(int id);
    Label addLabel(Label label);
    Label removeLabel(Label label);
}
