package task.ipv4_checker


fun isValidIPv4(address: String): Boolean {
   // return false
    val segments = address.split(".")

    // التحقق من أن العنوان يحتوي على 4 مقاطع فقط
    if (segments.size != 4) return false

    for (segment in segments) {
        // التحقق من أن المقطع ليس فارغا
        if (segment.isEmpty()) return false

        // التحقق من أن المقطع يحتوي فقط على أرقام
        if (!segment.all { it.isDigit() }) return false

        // تحويل النص إلى رقم والتحقق من أنه بين 0 و 255
        val num = segment.toIntOrNull() ?: return false
        if (num !in 0..255) return false

        // التحقق من عدم وجود أصفار بادئة
        if (segment.length > 1 && segment[0] == '0') return false
    }

    return true
}
fun main() {
    // ✅ عناوين IPv4 صحيحة
    check("Test 1 - Valid IPv4", isValidIPv4("192.168.1.1"), true)
    check("Test 2 - Valid IPv4", isValidIPv4("10.0.0.1"), true)
    check("Test 3 - Valid IPv4", isValidIPv4("255.255.255.255"), true)
    check("Test 4 - Valid IPv4", isValidIPv4("0.0.0.0"), true)

    // ❌ عناوين IPv4 غير صحيحة
    check("Test 5 - Out of range", isValidIPv4("256.100.50.25"), false) // 256 غير صالح
    check("Test 6 - Missing segment", isValidIPv4("192.168.1"), false) // أقل من 4 مقاطع
    check("Test 7 - Extra segment", isValidIPv4("192.168.1.1.1"), false) // أكثر من 4 مقاطع
    check("Test 8 - Leading zero", isValidIPv4("192.168.01.1"), false) // صفر بادئ غير مسموح
    check("Test 9 - Contains letters", isValidIPv4("192.168.1.a"), false) // يحتوي على حرف
    check("Test 10 - Negative number", isValidIPv4("192.168.1.-1"), false) // رقم سلبي
    check("Test 11 - Contains spaces", isValidIPv4("192.168. 1.1"), false) // يحتوي على مسافات
    check("Test 12 - Double dots", isValidIPv4("192..168.1.1"), false) // نقطتان متتاليتان
    check("Test 13 - Double dots", isValidIPv4("-192.168.1.1"), false) // يحتوي على رموز
    check("Test 14 - Valid IPv4", isValidIPv4("A.0.0.0"), false)// يحتوي على نص

}
fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("✅ Success - $name")
    } else {
        println("❌ Failed - $name")
    }
}
