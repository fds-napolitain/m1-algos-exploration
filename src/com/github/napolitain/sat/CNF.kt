package com.github.napolitain.sat

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

class CNF(path: String) {

	// (a ou b) et (a ou c)
	private val atoms: Set<Atom>
	private val cnf: LinkedList<LinkedList<Atom>>

	init {
		val fileName: String = File(".").canonicalPath + "/src/" + path
		val bufferedReader = BufferedReader(FileReader(fileName))
		val iterator = bufferedReader.lineSequence().iterator()
		atoms = mutableSetOf()
		cnf = LinkedList<LinkedList<Atom>>()
		while (iterator.hasNext()) {
			val electrons: String = iterator.next()
			cnf.add(LinkedList())
			for (electron in electrons.split(" ")) {
				val atom: Atom = if (electron.startsWith("!")) {
					Atom(electron[1].toInt())
				} else {
					Atom(electron[0].toInt())
				}
				atoms.add(atom)
				cnf.last.add(!atom)
			}
		}
		bufferedReader.close();
	}

}