package app.tier.persistence

data class CityInfo(
        val cityId: Float,
        val southWest: Coordinates,
        val northEast: Coordinates)