package be.pxl.budgetplanner.dao;

import be.pxl.budgetplanner.data.Label;

import java.util.List;

public interface LabelDAO {
    List<Label> getLabels();
    Label getLabel(int id);
    void addLabel(Label label);
    void removeLabel(Label label);
}
