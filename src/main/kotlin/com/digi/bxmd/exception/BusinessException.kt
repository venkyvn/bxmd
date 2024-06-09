package com.digi.bxmd.exception

import lombok.ToString

@ToString
class BusinessException(override val message: String, vararg val args: Any?) : Exception()
