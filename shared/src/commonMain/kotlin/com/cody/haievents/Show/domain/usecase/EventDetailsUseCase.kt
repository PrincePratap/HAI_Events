package com.cody.haievents.Show.domain.usecase

import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.TicketTypeRequest
import com.cody.haievents.common.util.Result
import kotlinx.datetime.LocalDate

class EventDetailsUseCase {

    operator fun invoke(
        eventTitle: String,
        organiserName: String,
        contactEmail: String,
        eventLocation: String,
        eventDate: String,          // YYYY-MM-DD, DD-MM-YYYY, or DD/MM/YYYY
        eventTime: String,          // HH:mm, h:mm AM/PM, or "h:mm AM/PM - h:mm AM/PM"
        eventDescription: String,
        ticketTypesList: List<TicketTypeRequest>
    ): Result<CreateUserEventRequest> {

        // -------- validations --------
        if (eventTitle.isBlank()) return Result.Error(message ="Event title cannot be empty")
        if (eventTitle.trim().length < 3) return Result.Error(message ="Event title should be at least 3 characters")

        if (organiserName.isBlank()) return Result.Error(message ="Organiser name cannot be empty")

//        if (!isValidEmail(contactEmail)) return Result.Error(message ="Please enter a valid email address")

        if (eventLocation.isBlank()) return Result.Error(message ="Event location cannot be empty")

        if (eventDescription.isBlank()) return Result.Error(message ="Event description cannot be empty")
        if (eventDescription.trim().length < 10) return Result.Error(message ="Event description should be at least 10 characters")

//        val normalizedDate = normalizeDateToIso(eventDate.trim())
//            ?: return Result.Error(message ="Invalid date. Use YYYY-MM-DD or DD-MM-YYYY or DD/MM/YYYY")
//
//        val normalizedTime = normalizeTimeOrRange(eventTime.trim())
//            ?: return Result.Error(message = "Invalid time. Use HH:mm, h:mm AM/PM, or a range like 6:30 PM - 9:30 PM")

        if (ticketTypesList.isEmpty()) return Result.Error(message ="Add at least one ticket type")

        // Validate & sanitize tickets (no !!; require role; trim name)
        val seen = mutableSetOf<String>()
        val cleanedTickets = mutableListOf<TicketTypeRequest>()

        ticketTypesList.forEachIndexed { idx, t ->
            val row = idx + 1
            val name = t.name?.trim().orEmpty()
            val qty  = t.quantity ?: 0
            val price = t.price ?: 0
            val role = t.role ?: return Result.Error(message ="Ticket #$row: role is required")

            if (name.isEmpty()) return Result.Error(message ="Ticket #$row: name cannot be empty")
            if (qty <= 0) return Result.Error(message ="Ticket #$row: quantity must be greater than 0")
            if (price < 0) return Result.Error(message ="Ticket #$row: price cannot be negative")

            val key = "${role.name}:${name.lowercase()}"
//            if (!seen.add(key)) {
//                return Result.Error(message ="Duplicate ticket for role '${pretty(role.name)}' with name '$name'")
//            }

            cleanedTickets += TicketTypeRequest(
                name = name,
                price = price,
                role = role,
                quantity = qty
            )
        }

        // -------- build sanitized payload (step 1 only) --------
        val payload = CreateUserEventRequest(
            title = eventTitle.trim(),
            description = eventDescription.trim(),
             organizerName = organiserName.trim(),
            email = contactEmail.trim(),
            location = eventLocation.trim(),
            date = "",             // normalized to YYYY-MM-DD
            time = "",             // "HH:mm" or "HH:mm - HH:mm"
            accountHolder = null,              // filled in Bank step
            bankName = null,
            ifscCode = null,
            accountNumber = null,
            bankPhoneNumber = null,
            ticketTypes = cleanedTickets
        )

        return Result.Success(payload)
    }

//    // ---------------- helpers (KMM-safe, no java.time, no String.format) ----------------
//
//    private fun isValidEmail(email: String): Boolean {
//        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
//        return regex.matches(email.trim())
//    }
//
//    /** Accepts: YYYY-MM-DD, DD-MM-YYYY, DD/MM/YYYY → returns ISO YYYY-MM-DD */
//    private fun normalizeDateToIso(input: String): String? {
//        val s = input.trim()
//        return when {
//            // ISO: YYYY-MM-DD
//            s.matches(Regex("\\d{4}-\\d{2}-\\d{2}")) -> {
//                val (y, m, d) = s.split("-").map { it.toInt() }
//                runCatching { LocalDate(y, m, d) }.getOrNull()?.let { isoDate(it.year, it.monthNumber, it.dayOfMonth) }
//            }
//            // DD-MM-YYYY
//            s.matches(Regex("\\d{2}-\\d{2}-\\d{4}")) -> {
//                val (d, m, y) = s.split("-").map { it.toInt() }
//                runCatching { LocalDate(y, m, d) }.getOrNull()?.let { isoDate(it.year, it.monthNumber, it.dayOfMonth) }
//            }
//            // DD/MM/YYYY
//            s.matches(Regex("\\d{2}/\\d{2}/\\d{4}")) -> {
//                val (d, m, y) = s.split("/").map { it.toInt() }
//                runCatching { LocalDate(y, m, d) }.getOrNull()?.let { isoDate(it.year, it.monthNumber, it.dayOfMonth) }
//            }
//            else -> null
//        }
//    }
//
//    /** Accepts single "HH:mm" or "h:mm AM/PM", or range "… - …" → returns "HH:mm" or "HH:mm - HH:mm" */
//    private fun normalizeTimeOrRange(input: String): String? {
//        val parts = input.split(" - ").map { it.trim() }
//        return when (parts.size) {
//            1 -> parseTimeTo24h(parts[0])
//            2 -> {
//                val start = parseTimeTo24h(parts[0]) ?: return null
//                val end = parseTimeTo24h(parts[1]) ?: return null
//                "$start - $end"
//            }
//            else -> null
//        }
//    }
//
//    /** Parses "HH:mm" or "h:mm AM/PM" → "HH:mm" */
//    private fun parseTimeTo24h(input: String): String? {
//        val s = input.trim().replace(Regex("\\s+"), " ")
//        // 12h with AM/PM
//        Regex("(?i)^(\\d{1,2}):(\\d{2})\\s*([AP]M)\$").matchEntire(s)?.let { m ->
//            val h = m.groupValues[1].toInt()
//            val min = m.groupValues[2].toInt()
//            val meridian = m.groupValues[3].uppercase()
//            if (h !in 1..12 || min !in 0..59) return null
//            val hh = when (meridian) {
//                "AM" -> if (h == 12) 0 else h
//                "PM" -> if (h == 12) 12 else h + 12
//                else -> return null
//            }
//            return hhmm(hh, min)
//        }
//        // 24h
//        Regex("^(\\d{1,2}):(\\d{2})\$").matchEntire(s)?.let { m ->
//            val h = m.groupValues[1].toInt()
//            val min = m.groupValues[2].toInt()
//            if (h in 0..23 && min in 0..59) return hhmm(h, min)
//        }
//        return null
//    }
//
//    // ---- tiny formatting helpers using padStart (KMM-safe) ----
//    private fun isoDate(y: Int, m: Int, d: Int): String =
//        "${y.toString().padStart(4, '0')}-${m.toString().padStart(2, '0')}-${d.toString().padStart(2, '0')}"
//
//    private fun hhmm(h: Int, m: Int): String =
//        "${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}"
//
//    private fun pretty(s: String) = s.lowercase().replaceFirstChar { it.titlecase() }
}
