package ru.contentforge.formconstructor.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StepSlider extends CustomFormElement {

    @Getter @SerializedName("default") protected int defaultIndex;
    @SerializedName("steps") protected List<String> options = new ArrayList<>();
    @Getter protected transient List<SelectableElement> elements = new ArrayList<>();
    @Getter protected transient int selectedIndex = -1;

    public StepSlider(List<SelectableElement> elements){
        this("", elements);
    }

    public StepSlider(String name, List<SelectableElement> elements){
        this(name, elements, 0);
    }

    public StepSlider(String name, List<SelectableElement> elements, int defaultIndex) {
        super(name, "step_slider");

        this.defaultIndex = defaultIndex;
        addElements(elements);
    }

    public void addElement(SelectableElement element){
        element.index = elements.size();
        elements.add(element);
        options.add(element.getText());
    }

    public void addElement(String name, Object value){
        addElement(new SelectableElement(name, value));
    }

    public void addElement(String name){
        addElement(name, null);
    }

    public void addElements(Collection<SelectableElement> elements){
        for (SelectableElement element: elements) {
            addElement(element);
        }
    }

    public SelectableElement getDefault(){
        return elements.get(defaultIndex);
    }

    public SelectableElement getValue(){
        return selectedIndex == -1? null : elements.get(selectedIndex);
    }

    @Override
    public void respond(Object value) {
        selectedIndex = ((Double) value).intValue();
    }

}