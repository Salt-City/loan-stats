package io.brandon.loanstats.model

enum class State {
    AL, AK, AZ, AR, AS,
    CA, CO, CT,
    DE, DC,
    FL,
    GA, GU,
    HI,
    ID, IL, IN, IA,
    KS, KY,
    LA,
    ME, MD, MA, MI, MN, MS, MO, MT, MP,
    NE, NV, NH, NJ, NM, NY, NC, ND,
    OH, OK, OR,
    PA, PR,
    RI,
    SC, SD,
    TN, TX, TT,
    UT,
    VT, VA, VI,
    WA, WV, WI, WY,
    UNKNOWN;

    companion object {
        fun fromString(s: String): State = when (s.lowercase()) {
            "alabama" -> AL
            "alaska" -> AK
            "arizona" -> AZ
            "arkansas" -> AR
            "american samoa" -> AS
            "california" -> CA
            "colorado" -> CO
            "connecticut" -> CT
            "delaware" -> DE
            "district of columbia" -> DC
            "florida" -> FL
            "georgia" -> GA
            "guam" -> GU
            "hawaii" -> HI
            "idaho" -> ID
            "illinois" -> IL
            "indiana" -> IN
            "iowa" -> IA
            "kansas" -> KS
            "kentucky" -> KY
            "louisiana" -> LA
            "maine" -> ME
            "maryland" -> MD
            "massachusetts" -> MA
            "michigan" -> MI
            "minnesota" -> MN
            "mississippi" -> MS
            "missouri" -> MO
            "montana" -> MT
            "nebraska" -> NE
            "nevada" -> NV
            "new hampshire" -> NH
            "new jersey" -> NJ
            "new mexico" -> NM
            "new york" -> NY
            "north carolina" -> NC
            "north dakota" -> ND
            "northern mariana islands" -> MP
            "ohio" -> OH
            "oklahoma" -> OK
            "oregon" -> OR
            "pennsylvania" -> PA
            "puerto rico" -> PR
            "rhode island" -> RI
            "south carolina" -> SC
            "south dakota" -> SD
            "tennessee" -> TN
            "texas" -> TX
            "trust territories" -> TT
            "utah" -> UT
            "vermont" -> VT
            "virginia" -> VA
            "virgin islands" -> VI
            "washington" -> WA
            "west virginia" -> WV
            "wisconsin" -> WI
            "wyoming" -> WY
            else -> UNKNOWN
        }
    }
}