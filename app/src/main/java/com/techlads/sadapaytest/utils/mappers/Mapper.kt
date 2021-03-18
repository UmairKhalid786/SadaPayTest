package com.techlads.swvl.utils.mappers

import com.techlads.swvl.utils.Resource


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.swvl.utils.mappers
 */


interface Mapper<I, O> {
    fun map(input: I?): Resource<O>
}