package com.example.localizedstring.entity

import com.google.common.truth.Truth
import org.junit.Test

class LocalizedStringUtilsKtTest {

    @Test
    fun `localizedString should return LocalizedIntId`() {
        val id = 1
        val args = arrayOf("arg1", "arg2")

        val result = localizedString(id, *args)

        Truth.assertThat(result).isInstanceOf(LocalizedIntId::class.java)
        Truth.assertThat((result as LocalizedIntId).id).isEqualTo(id)
        Truth.assertThat(result.args).containsExactly(*args)
    }

    @Test
    fun `localizedString should return LocalizedStringId`() {
        val id = "id"
        val args = arrayOf("arg1", "arg2")

        val result = localizedString(id, *args)

        Truth.assertThat(result).isInstanceOf(LocalizedStringId::class.java)
        Truth.assertThat((result as LocalizedStringId).id).isEqualTo(id)
        Truth.assertThat(result.args).containsExactly(*args)
    }

    @Test
    fun `localizedRowString should return LocalizedRawString`() {
        val string = "string"

        val result = localizedRowString(string)

        Truth.assertThat(result).isInstanceOf(LocalizedRawString::class.java)
        Truth.assertThat((result as LocalizedRawString).string).isEqualTo(string)
    }

    @Test
    fun `localizedString should return LocalizedEmptyString`() {
        val result = localizedString()

        Truth.assertThat(result).isInstanceOf(LocalizedEmptyString::class.java)
    }

    @Test
    fun `toLocalizedString should return LocalizedIntId`() {
        val id = 1
        val args = arrayOf("arg1", "arg2")

        val result = id.toLocalizedString(*args)

        Truth.assertThat(result).isInstanceOf(LocalizedIntId::class.java)
        Truth.assertThat((result as LocalizedIntId).id).isEqualTo(id)
        Truth.assertThat(result.args).containsExactly(*args)
    }

    @Test
    fun `toLocalizedString should return LocalizedStringId`() {
        val id = "id"
        val args = arrayOf("arg1", "arg2")

        val result = id.toLocalizedString(*args)

        Truth.assertThat(result).isInstanceOf(LocalizedStringId::class.java)
        Truth.assertThat((result as LocalizedStringId).id).isEqualTo(id)
        Truth.assertThat(result.args).containsExactly(*args)
    }

    @Test
    fun `toLocalizedRowString should return LocalizedRawString`() {
        val string = "string"

        val result = string.toLocalizedRowString()

        Truth.assertThat(result).isInstanceOf(LocalizedRawString::class.java)
        Truth.assertThat((result as LocalizedRawString).string).isEqualTo(string)
    }

    @Test
    fun `withArgs should return LocalizedIntId`() {
        val id = 1
        val args = arrayOf("arg1", "arg2")
        val localizedString = LocalizedIntId(id, args.toList())

        val result = localizedString.withArgs(*args)

        Truth.assertThat(result).isInstanceOf(LocalizedIntId::class.java)
        Truth.assertThat((result as LocalizedIntId).id).isEqualTo(id)
        Truth.assertThat(result.args).containsExactly(*args)
    }
}
