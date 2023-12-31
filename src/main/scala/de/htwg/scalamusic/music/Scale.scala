package de.htwg.scalamusic.music

case class Scale(base: Key, scaleType: ScaleType = ScaleType.Major) extends MusicSeq:
    val musicSeq: Vector[Key] = scaleType
        .scaleVector(scaleType)
        .map(i => base.copy(midiNumber = base.midiNumber + i))
    override def toString = base.toString + "Scale"

enum ScaleType:
    case Major, Minor, Melodic, Harmonic, PentatonicMinorBlues,
        PentatonicMajorBlues, Ionian, Dorian, Phrygian, Lydian, Mixolydian,
        Aeolian, Locrian, PentatonicMinor, PentatonicMajor

    val scaleVector = Map[ScaleType, Vector[Int]](
      Major -> Vector(0, 2, 4, 5, 7, 9, 11, 12),
      Minor -> Vector(0, 2, 3, 5, 7, 8, 10, 12),
      Melodic -> Vector(0, 2, 3, 5, 7, 9, 11, 12),
      Harmonic -> Vector(0, 2, 3, 5, 7, 8, 11, 12),
      PentatonicMinorBlues -> Vector(0, 3, 5, 6, 7, 10, 12),
      PentatonicMajorBlues -> Vector(0, 2, 3, 4, 7, 9, 12),
      Ionian -> Vector(0, 2, 4, 5, 7, 9, 11, 12),
      Dorian -> Vector(0, 2, 3, 5, 7, 9, 10, 12),
      Phrygian -> Vector(0, 1, 3, 5, 7, 8, 10, 12),
      Lydian -> Vector(0, 2, 4, 6, 7, 9, 11, 12),
      Mixolydian -> Vector(0, 2, 4, 5, 7, 9, 10, 12),
      Aeolian -> Vector(0, 2, 3, 5, 7, 8, 10, 12),
      Locrian -> Vector(0, 1, 3, 5, 6, 8, 10, 12),
      PentatonicMinor -> Vector(0, 3, 5, 7, 10, 12),
      PentatonicMajor -> Vector(0, 2, 4, 7, 9, 12)
    )
