package com.potato112.springdemo.web.form.listeners;

import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.Map;
import java.util.stream.Stream;

public class BinderWithValueChangeListener<T> extends BeanValidationBinder<T> {

    private Registration registration;
    private FormModificationListener listener;

    public BinderWithValueChangeListener(Class<T> beanType) {
        super(beanType);
    }

    private void resetValueChangeListener() {

        if (null != registration) {
            registration.remove();
        }
        this.listener = new FormModificationListener();
        this.registration = addValueChangeListener(listener);
    }

    @Override
    public void setBean(T o) {
        super.setBean(o);
        resetValueChangeListener();
    }

    @Override
    public void removeBean() {
        super.removeBean();
        resetValueChangeListener();
    }

    @Override
    public void writeBean(T o) throws ValidationException {
        super.writeBean(o);
        resetValueChangeListener();
    }

    @Override
    public boolean writeBeanIfValid(T o) {

        boolean warWritted = super.writeBeanIfValid(o);
        if (warWritted) {
            resetValueChangeListener();
        }
        return warWritted;
    }

    @Override
    protected void restoreBeanState(T t, Map<Binding<T, ?>, Object> oldValues) {
        super.restoreBeanState(t, oldValues);
        resetValueChangeListener();
    }

    public boolean wasModified() {
        if (null == listener) {
            return false;
        }
        return listener.isChanged();
    }

    public void clearValidationError() {

        Stream<HasValue<?, ?>> fields = this.getFields();
        fields.forEach(field -> {
            if (field instanceof HasValidation) {
                HasValidation hasValidation = (HasValidation) field;
                hasValidation.setErrorMessage(null);
                hasValidation.setInvalid(false);
                field.setRequiredIndicatorVisible(false);
            }
        });
    }
}
