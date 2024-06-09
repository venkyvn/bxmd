package com.digi.bxmd.constant

object ValidatorConstant {
    /**
     * The minimum length of username(email).
     */
    const val USERNAME_MIN_LENGTH = 4

    /**
     * The maximum length of username(email).
     */
    const val USERNAME_MAX_LENGTH = 30

    /**
     * The minimum length of password.
     */
    const val PASSWORD_MIN_LENGTH = 8

    /**
     * The maximum length of password.
     */
    const val PASSWORD_MAX_LENGTH = 20

    /**
     * The maximum length of file size.
     */
    const val FILE_SIZE_MAX = 5000000

    /**
     * The maximum length of name.
     */
    const val NAME_MAX_LENGTH = 50

    /**
     * Email pattern.
     */
    const val EMAIL_REGEX_EXPRESSION = "^[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z]+(\\.[a-zA-Z]{1,4}){1,3}$"

    /**
     * Password pattern.
     */
    const val PASSWORD_REGEX_EXPRESSION = "^(?=.{8,20}$)(?=.*[a-zA-Z])(?=.*[0-9])(?=.*\\W).*$"
}