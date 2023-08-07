package com.glechyk.gram.delta.core.utils

import android.icu.util.Calendar
import android.icu.util.TimeZone
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker

private val today = MaterialDatePicker.todayInUtcMilliseconds()
private val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

private val startCalendarConstraint by lazy {
    calendar[Calendar.DAY_OF_MONTH] = Constants.FIRST_DAY_OF_MONTH
    calendar[Calendar.MONTH] = Calendar.JANUARY
    calendar[Calendar.YEAR] = Constants.START_CALENDAR_CONSTRAINT_YEAR
    calendar.timeInMillis
}

private val endCalendarConstraint by lazy {
    calendar.timeInMillis = today
    calendar.add(Calendar.YEAR, -Constants.MIN_USER_AGE)
    calendar.timeInMillis
}

private val calendarConstraints by lazy {
    CalendarConstraints.Builder()
        .setStart(startCalendarConstraint)
        .setEnd(endCalendarConstraint)
        .setValidator(DateValidatorPointBackward.before(endCalendarConstraint))
        .build()
}

val commonBirthdayMaterialDatePicker = MaterialDatePicker.Builder.datePicker()
    .setTitleText(Constants.DATE_PICKER_TITLE)
    .setSelection(endCalendarConstraint)
    .setCalendarConstraints(calendarConstraints)
    .build()
