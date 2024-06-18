package com.digi.bxmd.constant

object MessageKey {
    const val UNAUTHORIZED = "error.auth.unauthorized"
    const val FORBIDDEN = "error.auth.forbidden"
    const val BAD_CREDENTIAL = "error.auth.bad.credential"
    const val BAD_REQUEST = "error.auth.bad.request"
    const val INVALID_REFRESH_TOKEN = "error.auth.invalid.refreshToken"

    const val SERVER_ERROR = "error.common.internal.server"
    const val NOT_FOUND = "error.common.notfound"
    const val ALREADY_UPDATE_MESSAGE = "error.common.already.updated"
    const val CAST_INT_ERR = "error.common.cast.int"
    const val CAST_ENUM_ERR = "error.common.cast.enum"
    const val CAST_SEARCH_FILTER_ERR = "error.common.cast.search.filter"
    const val VALIDATION_ERR = "error.common.validation"

    const val USER_NOT_FOUND = "error.user.notfound"
    const val USER_EMAIL_OR_USERNAME_EXISTED = "error.email.username.existed"
    const val CURRENT_PASSWORD_NOT_CORRECT = "error.current.password.not.correct"
    const val PASSWORD_NOT_MATCH_PATTERN = "error.password.not.match.pattern"

    const val VALIDATION_INVALID_EMAIL = "validation.invalid.email"
    const val VALIDATION_NOT_EMPTY = "validation.not.empty"
    const val VALIDATION_MAX_LENGTH = "validation.max.length"
    const val VALIDATION_MAX_FILE_SIZE = "validation.max.file.size"
    const val VALIDATION_SUITE_NAME_ALREADY_EXIST = "validation.suite.name.already.exist"
    const val VALIDATION_PROJECT_KEY_ALREADY_EXIST = "validation.project.key.already.exist"
    const val VALIDATION_ADD_TEST_CASE = "validation.add.test.case.invalid.status"
    const val VALIDATION_RUN_TEST_SUITE = "validation.run.test.suite"
    const val VALIDATION_INVALID_DATE_FORMAT = "validation.invalid.date.format"

    const val VALIDATION_TESTCASE_IN_EXECUTING_TESTSUITE = "validation.testcase.in.executing.testExecution"
    const val VALIDATION_TEST_ENVIRONMENT_IN_EXECUTING_TESTSUITE =
        "validation.test.environment.in.executing.testExecution"
    const val INVALID_CHANGE_TEST_ENVIRONMENT = "invalid.change.test.environment"

    const val VALIDATION_EXECUTION_STATUS = "validation.execution.status"
    const val INVALID_TEST_CASE_STATUS = "invalid.test.case.status"
    const val INVALID_TEST_EXECUTION = "error.invalid.test.execution"

    const val DASH_BOARD_TEST_EXECUTION_CYCLE_NAME = "dashboard.testExecution.cycle.name"

    const val DOWNLOAD_FILE_TOO_LARGE = "invalid.download.file.number.row"}