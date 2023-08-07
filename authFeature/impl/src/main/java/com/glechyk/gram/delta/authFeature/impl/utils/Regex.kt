package com.glechyk.gram.delta.authFeature.impl.utils

private val EMAIL_REGEX = Regex("^([a-zA-Z\\d.+_%\\-]+@[a-zA-Z\\d.\\-]+\\.[a-zA-Z]{2,6})*\$")

private val PHONE_NUMBER_REGEX = Regex("\\d{9}")

private val AT_LEAST_ONE_LOWER_CASE_REGEX = Regex("(.*[a-z].*)")

private val AT_LEAST_ONE_UPPER_CASE_REGEX = Regex("(.*[A-Z].*)")

private val AT_LEAST_ONE_DIGIT_REGEX = Regex("(.*\\d.*)")

private val AT_LEAST_ONE_SYMBOL_REGEX = Regex("(.*[/.*?~!@#\$%^&()_\\-\\]\\[+=|\\\\,{}`'\";:><].*)")

private val NICKNAME_REGEX = Regex("^[-a-zA-Z\\d_'.]+\$")

private val FULL_NAME_REGEX = Regex("^([ёЁа-яА-Яa-zA-Z'\\-]+\\s)*[ёЁа-яА-Яa-zA-Z'\\-]+\$")

fun String.isEmail() = EMAIL_REGEX.matches(this)

fun String.isNotEmail() = !this.isEmail()

fun String.isPhoneNumber() = PHONE_NUMBER_REGEX.matches(this)

fun String.isNotPhoneNumber() = !this.isPhoneNumber()

fun String.isHasAtLeastOneLowerCaseLetter() = AT_LEAST_ONE_LOWER_CASE_REGEX.matches(this)

fun String.isNotHasAtLeastOneLowerCaseLetter() = !this.isHasAtLeastOneLowerCaseLetter()

fun String.isHasAtLeastOneUpperCaseLetter() = AT_LEAST_ONE_UPPER_CASE_REGEX.matches(this)

fun String.isNotHasAtLeastOneUpperCaseLetter() = !this.isHasAtLeastOneUpperCaseLetter()

fun String.isHasAtLeastOneDigit() = AT_LEAST_ONE_DIGIT_REGEX.matches(this)

fun String.isNotHasAtLeastOneDigit() = !this.isHasAtLeastOneDigit()

fun String.isHasAtLeastOneSymbol() = AT_LEAST_ONE_SYMBOL_REGEX.matches(this)

fun String.isNotHasAtLeastOneSymbol() = !this.isHasAtLeastOneSymbol()

fun String.isNickname() = NICKNAME_REGEX.matches(this)

fun String.isNotNickname() = !this.isNickname()

fun String.isFullName() = FULL_NAME_REGEX.matches(this)

fun String.isNotFullName() = !this.isFullName()
