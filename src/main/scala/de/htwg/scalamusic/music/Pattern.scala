package de.htwg.scalamusic.music

case class Pattern(pattern: Int*) extends Iterable[Int] {
  // def foreach[U](f: Int => U) = pattern.foreach{ f}
  def iterator: Iterator[Int] = pattern.iterator
}
