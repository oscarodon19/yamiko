package ar.edu.ungs.sail

import scalax.collection.immutable.Graph
import scalax.collection.edge.WLUnDiEdge
import scala.collection.mutable.ListBuffer
import scalax.collection.edge.WUnDiEdge

@SerialVersionUID(1L)
class Carr40 extends VMG{

  var speeds=Array.ofDim[Double](181,31)
  speeds(32)=Array(2.2,2.75,3.3,3.75,4.2,4.55,4.9,5.05,5.2,5.35,5.5,5.55,5.6,5.625,5.65,5.675,5.7,5.7,5.7,5.7,5.7,5.7,5.64,5.58,5.52,5.46,5.4,5.18,4.96,4.74,4.52,4.3)
  speeds(36)=Array(2.6,3.25,3.9,4.35,4.8,5.1,5.4,5.55,5.7,5.8,5.9,5.95,6,6.05,6.1,6.15,6.2,6.2,6.2,6.2,6.2,6.2,6.18,6.16,6.14,6.12,6.1,6.04,5.98,5.92,5.86,5.8)
  speeds(40)=Array(3,3.65,4.3,4.75,5.2,5.5,5.8,5.95,6.1,6.15,6.2,6.25,6.3,6.35,6.4,6.45,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.46,6.42,6.38,6.34,6.3)
  speeds(45)=Array(3.3,4,4.7,5.2,5.7,5.9,6.1,6.25,6.4,6.45,6.5,6.55,6.6,6.625,6.65,6.675,6.7,6.72,6.74,6.76,6.78,6.8,6.8,6.8,6.8,6.8,6.8,6.78,6.76,6.74,6.72,6.7)
  speeds(52)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(60)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(70)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(80)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(90)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(100)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(110)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(120)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(130)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(140)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(150)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(160)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(170)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(180)=Array(1.9,2.4,2.9,3.4,3.9,4.35,4.8,5.2,5.6,5.95,6.3,6.55,6.8,7,7.2,7.4,7.6,7.78,7.96,8.14,8.32,8.5,8.8,9.1,9.4,9.7,10,10.4,10.8,11.2,11.6,12)

  
  speeds(33)=Array(2.2,2.75,3.3,3.75,4.2,4.55,4.9,5.05,5.2,5.35,5.5,5.55,5.6,5.625,5.65,5.675,5.7,5.7,5.7,5.7,5.7,5.7,5.64,5.58,5.52,5.46,5.4,5.18,4.96,4.74,4.52,4.3)
  speeds(34)=Array(2.2,2.75,3.3,3.75,4.2,4.55,4.9,5.05,5.2,5.35,5.5,5.55,5.6,5.625,5.65,5.675,5.7,5.7,5.7,5.7,5.7,5.7,5.64,5.58,5.52,5.46,5.4,5.18,4.96,4.74,4.52,4.3)
  speeds(35)=Array(2.6,3.25,3.9,4.35,4.8,5.1,5.4,5.55,5.7,5.8,5.9,5.95,6,6.05,6.1,6.15,6.2,6.2,6.2,6.2,6.2,6.2,6.18,6.16,6.14,6.12,6.1,6.04,5.98,5.92,5.86,5.8)
  speeds(37)=Array(2.6,3.25,3.9,4.35,4.8,5.1,5.4,5.55,5.7,5.8,5.9,5.95,6,6.05,6.1,6.15,6.2,6.2,6.2,6.2,6.2,6.2,6.18,6.16,6.14,6.12,6.1,6.04,5.98,5.92,5.86,5.8)
  speeds(38)=Array(2.6,3.25,3.9,4.35,4.8,5.1,5.4,5.55,5.7,5.8,5.9,5.95,6,6.05,6.1,6.15,6.2,6.2,6.2,6.2,6.2,6.2,6.18,6.16,6.14,6.12,6.1,6.04,5.98,5.92,5.86,5.8)
  speeds(39)=Array(3,3.65,4.3,4.75,5.2,5.5,5.8,5.95,6.1,6.15,6.2,6.25,6.3,6.35,6.4,6.45,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.46,6.42,6.38,6.34,6.3)
  speeds(41)=Array(3,3.65,4.3,4.75,5.2,5.5,5.8,5.95,6.1,6.15,6.2,6.25,6.3,6.35,6.4,6.45,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.46,6.42,6.38,6.34,6.3)
  speeds(42)=Array(3,3.65,4.3,4.75,5.2,5.5,5.8,5.95,6.1,6.15,6.2,6.25,6.3,6.35,6.4,6.45,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.46,6.42,6.38,6.34,6.3)
  speeds(43)=Array(3,3.65,4.3,4.75,5.2,5.5,5.8,5.95,6.1,6.15,6.2,6.25,6.3,6.35,6.4,6.45,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.5,6.46,6.42,6.38,6.34,6.3)
  speeds(44)=Array(3.3,4,4.7,5.2,5.7,5.9,6.1,6.25,6.4,6.45,6.5,6.55,6.6,6.625,6.65,6.675,6.7,6.72,6.74,6.76,6.78,6.8,6.8,6.8,6.8,6.8,6.8,6.78,6.76,6.74,6.72,6.7)
  speeds(46)=Array(3.3,4,4.7,5.2,5.7,5.9,6.1,6.25,6.4,6.45,6.5,6.55,6.6,6.625,6.65,6.675,6.7,6.72,6.74,6.76,6.78,6.8,6.8,6.8,6.8,6.8,6.8,6.78,6.76,6.74,6.72,6.7)
  speeds(47)=Array(3.3,4,4.7,5.2,5.7,5.9,6.1,6.25,6.4,6.45,6.5,6.55,6.6,6.625,6.65,6.675,6.7,6.72,6.74,6.76,6.78,6.8,6.8,6.8,6.8,6.8,6.8,6.78,6.76,6.74,6.72,6.7)
  speeds(48)=Array(3.3,4,4.7,5.2,5.7,5.9,6.1,6.25,6.4,6.45,6.5,6.55,6.6,6.625,6.65,6.675,6.7,6.72,6.74,6.76,6.78,6.8,6.8,6.8,6.8,6.8,6.8,6.78,6.76,6.74,6.72,6.7)
  speeds(49)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(50)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(51)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(53)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(54)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(55)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(56)=Array(3.7,4.45,5.2,5.65,6.1,6.3,6.5,6.55,6.6,6.7,6.8,6.85,6.9,6.925,6.95,6.975,7,7.02,7.04,7.06,7.08,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1,7.1)
  speeds(57)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(58)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(59)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(61)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(62)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(63)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(64)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(65)=Array(4.1,4.85,5.6,6,6.4,6.55,6.7,6.8,6.9,6.95,7,7.05,7.1,7.125,7.15,7.175,7.2,7.22,7.24,7.26,7.28,7.3,7.32,7.34,7.36,7.38,7.4,7.4,7.4,7.4,7.4,7.4)
  speeds(66)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(67)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(68)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(69)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(71)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(72)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(73)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(74)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(75)=Array(4.3,5.05,5.8,6.2,6.6,6.75,6.9,7,7.1,7.15,7.2,7.25,7.3,7.35,7.4,7.45,7.5,7.54,7.58,7.62,7.66,7.7,7.72,7.74,7.76,7.78,7.8,7.8,7.8,7.8,7.8,7.8)
  speeds(76)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(77)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(78)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(79)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(81)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(82)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(83)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(84)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(85)=Array(4.4,5.15,5.9,6.25,6.6,6.8,7,7.15,7.3,7.35,7.4,7.45,7.5,7.575,7.65,7.725,7.8,7.82,7.84,7.86,7.88,7.9,7.94,7.98,8.02,8.06,8.1,8.1,8.1,8.1,8.1,8.1)
  speeds(86)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(87)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(88)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(89)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(91)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(92)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(93)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(94)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(95)=Array(4.5,5.3,6.1,6.4,6.7,6.85,7,7.15,7.3,7.45,7.6,7.65,7.7,7.775,7.85,7.925,8,8.02,8.04,8.06,8.08,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4)
  speeds(96)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(97)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(98)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(99)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(101)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(102)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(103)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(104)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(105)=Array(4.5,5.3,6.1,6.45,6.8,6.95,7.1,7.2,7.3,7.4,7.5,7.65,7.8,7.875,7.95,8.025,8.1,8.14,8.18,8.22,8.26,8.3,8.32,8.34,8.36,8.38,8.4,8.44,8.48,8.52,8.56,8.6)
  speeds(106)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(107)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(108)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(109)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(111)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(112)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(113)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(114)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(115)=Array(4.4,5.2,6,6.35,6.7,6.95,7.2,7.3,7.4,7.5,7.6,7.7,7.8,7.9,8,8.1,8.2,8.26,8.32,8.38,8.44,8.5,8.58,8.66,8.74,8.82,8.9,8.96,9.02,9.08,9.14,9.2)
  speeds(116)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(117)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(118)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(119)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(121)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(122)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(123)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(124)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(125)=Array(4.1,4.85,5.6,6.05,6.5,6.8,7.1,7.3,7.5,7.65,7.8,7.9,8,8.1,8.2,8.3,8.4,8.52,8.64,8.76,8.88,9,9.1,9.2,9.3,9.4,9.5,9.62,9.74,9.86,9.98,10.1)
  speeds(126)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(127)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(128)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(129)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(131)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(132)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(133)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(134)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(135)=Array(3.6,4.35,5.1,5.65,6.2,6.5,6.8,7.05,7.3,7.5,7.7,7.85,8,8.175,8.35,8.525,8.7,8.88,9.06,9.24,9.42,9.6,9.76,9.92,10.08,10.24,10.4,10.56,10.72,10.88,11.04,11.2)
  speeds(136)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(137)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(138)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(139)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(141)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(142)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(143)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(144)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(145)=Array(3.1,3.8,4.5,5.1,5.7,6.1,6.5,6.75,7,7.2,7.4,7.65,7.9,8.1,8.3,8.5,8.7,8.94,9.18,9.42,9.66,9.9,10.16,10.42,10.68,10.94,11.2,11.46,11.72,11.98,12.24,12.5)
  speeds(146)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(147)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(148)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(149)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(151)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(152)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(153)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(154)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(155)=Array(2.6,3.25,3.9,4.45,5,5.5,6,6.3,6.6,6.85,7.1,7.3,7.5,7.725,7.95,8.175,8.4,8.72,9.04,9.36,9.68,10,10.42,10.84,11.26,11.68,12.1,12.5,12.9,13.3,13.7,14.1)
  speeds(156)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(157)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(158)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(159)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(161)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(162)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(163)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(164)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(165)=Array(2.3,2.85,3.4,3.95,4.5,5,5.5,5.9,6.3,6.55,6.8,7.05,7.3,7.5,7.7,7.9,8.1,8.38,8.66,8.94,9.22,9.5,9.92,10.34,10.76,11.18,11.6,12.14,12.68,13.22,13.76,14.3)
  speeds(166)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(167)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(168)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(169)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(171)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(172)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(173)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(174)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(175)=Array(2.1,2.6,3.1,3.6,4.1,4.6,5.1,5.5,5.9,6.25,6.6,6.8,7,7.2,7.4,7.6,7.8,8.04,8.28,8.52,8.76,9,9.36,9.72,10.08,10.44,10.8,11.28,11.76,12.24,12.72,13.2)
  speeds(176)=Array(1.9,2.4,2.9,3.4,3.9,4.35,4.8,5.2,5.6,5.95,6.3,6.55,6.8,7,7.2,7.4,7.6,7.78,7.96,8.14,8.32,8.5,8.8,9.1,9.4,9.7,10,10.4,10.8,11.2,11.6,12)
  speeds(177)=Array(1.9,2.4,2.9,3.4,3.9,4.35,4.8,5.2,5.6,5.95,6.3,6.55,6.8,7,7.2,7.4,7.6,7.78,7.96,8.14,8.32,8.5,8.8,9.1,9.4,9.7,10,10.4,10.8,11.2,11.6,12)
  speeds(178)=Array(1.9,2.4,2.9,3.4,3.9,4.35,4.8,5.2,5.6,5.95,6.3,6.55,6.8,7,7.2,7.4,7.6,7.78,7.96,8.14,8.32,8.5,8.8,9.1,9.4,9.7,10,10.4,10.8,11.2,11.6,12)
  speeds(179)=Array(1.9,2.4,2.9,3.4,3.9,4.35,4.8,5.2,5.6,5.95,6.3,6.55,6.8,7,7.2,7.4,7.6,7.78,7.96,8.14,8.32,8.5,8.8,9.1,9.4,9.7,10,10.4,10.8,11.2,11.6,12)
  
  
  override def getSpeed(angle:Int, windspeed:Int):Double={
      var ws:Int=windspeed
      var an:Int=angle
      if (an<32) return 0d;
      if (ws>35) ws=35;
      if (ws<4) return 0d;
      ws=ws-4;
      if (an>360) an=an % 360;
      if (an>180) an=360-an
      speeds(an)(ws)      
  }
  
  override def getMaxSpeed(windspeed:Int):(Int,Double)={
      var ws:Int=windspeed
      if (ws>35) ws=35;
      if (ws<4) return (0,0d)
      ws=ws-4;
      var i:Int=0
      var angle:Int=0
      var max:Double=0
      for( i <- 32 to 180) if (speeds(i)(ws)>max) {
          max=speeds(i)(ws);
          angle=i
      }
      (angle,max)
      
  }

  
}

@SerialVersionUID(1L)
class Nodo(_id:String,_cuadrantes:List[(Int,Int)]) extends Serializable
{
  val id=_id
  val cuadrante=_cuadrantes
  override def toString = id
}

@SerialVersionUID(1L)
class CanchaRioDeLaPlata(_dimension:Int, _nodosPorCelda:Int, _metrosPorLadoCelda:Int) extends Cancha
{
  val dimension=_dimension
  val nodosPorCelda=_nodosPorCelda
  val metrosPorLadoCelda=_metrosPorLadoCelda

  var nodos=ListBuffer[Nodo]()
  var arcos=ListBuffer[WUnDiEdge[Nodo]]()
  
  //Armar grafo
  for (x <- 0 to dimension*nodosPorCelda-1)
    for (y <- 0 to dimension*nodosPorCelda-1)      
      if(x==0 || (x+1)%dimension==0 || y==0 || (y+1)%dimension==0 )
        nodos+=new Nodo("("+x+")"+"("+y+")",armarCuadrantes(x, y));
	
  for (x <- 0 to dimension-1)
    for (y <- 0 to dimension-1)     
      ((nodos.filter(p=>p.cuadrante.contains((x,y))).combinations(2)).foreach(f=>arcos+=WUnDiEdge(f(0),f(1))(0l)))
  
  val vertex=nodos.toList
  val edges=arcos.toList
      
  val graph=Graph.from(vertex, edges)
  
  private def armarCuadrantes(x:Int,y:Int):List[(Int,Int)]={
    val xx=x/dimension
    val yy=y/dimension
    var salida=ListBuffer((xx,yy))
    if (x>0 && x<dimension*nodosPorCelda-1 && (x+1)%dimension==0) salida+=((xx+1,yy))
    if (y>0 && y<dimension*nodosPorCelda-1 && (y+1)%dimension==0) salida+=((xx,yy+1))
    if (x>0 && x<dimension*nodosPorCelda-1 && (x+1)%dimension==0 && y>0 && y<dimension*nodosPorCelda-1 && (y+1)%dimension==0) salida+=((xx+1,yy+1))    
    salida.toList
  }
  
  override def getGraph():Graph[Nodo,WUnDiEdge]=graph
  override def getDimension():Int=dimension
  override def getNodosPorCelda():Int=nodosPorCelda
  override def getMetrosPorLadoCelda():Int=metrosPorLadoCelda
  override def getNodos():List[Nodo]=vertex
  override def getArcos():List[WUnDiEdge[Nodo]]=edges

}
