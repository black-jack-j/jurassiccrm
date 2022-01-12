package com.jurassic.jurassiccrm.task.dto.validation;

import com.jurassic.jurassiccrm.task.dto.TaskTO;
import com.jurassic.jurassiccrm.task.dto.validation.exception.TaskValidationException;

public interface TaskTOValidator {

    void validate(TaskTO validate) throws TaskValidationException;

}
