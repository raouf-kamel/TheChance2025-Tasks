package task.ipv4_checker


fun isValidIPv4(address: String): Boolean {
   // return false
    val segments = address.split(".")

    if (segments.size != 4) return false

    for (segment in segments) {
        if (segment.isEmpty()) return false

        if (!segment.all { it.isDigit() }) return false

        val num = segment.toIntOrNull() ?: return false
        if (num !in 0..255) return false

        if (segment.length > 1 && segment[0] == '0') return false
    }

    return true
}

fun main() {
    // ✅ Valid IPs
    check("When the IP is valid, then return true", isValidIPv4("192.168.1.1"), true)
    check("When the IP is valid, then return true", isValidIPv4("10.0.0.1"), true)
    check("When the IP is valid, then return true", isValidIPv4("255.255.255.255"), true)
    check("When the IP is valid, then return true", isValidIPv4("0.0.0.0"), true)

    // ❌ Invalid IPs
    check("When the IP is out of range, then return false", isValidIPv4("256.100.50.25"), false)
    check("When the IP is missing a segment, then return false", isValidIPv4("192.168.1"), false)
    check("When the IP has extra segment, then return false", isValidIPv4("192.168.1.1.1"), false)
    check("When the IP has leading zero, then return false", isValidIPv4("192.168.01.1"), false)
    check("When the IP contains letters, then return false", isValidIPv4("192.168.1.a"), false)
    check("When the IP contains negative number, then return false", isValidIPv4("192.168.1.-1"), false)
    check("When the IP contains spaces, then return false", isValidIPv4("192.168. 1.1"), false)
    check("When the IP contains double dots, then return false", isValidIPv4("192..168.1.1"), false)
    check("When the IP uses comma instead of dot, then return false", isValidIPv4("192,168,1,1"), false)
    check("When the IP contains special characters, then return false", isValidIPv4("192.168.@.1"), false)
    check("When the IP has empty segment, then return false", isValidIPv4("192.168..1"), false)
    check("When the IP is an empty string, then return false", isValidIPv4(""), false)
}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    when (result) {
        correctResult -> {
            println("  Success - $name")
        }
        else -> {
            println("  Failed - $name")
        }
    }
}
