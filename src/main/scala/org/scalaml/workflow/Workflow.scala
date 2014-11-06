/**
 * Copyright 2013, 2014  by Patrick Nicolas - Scala for Machine Learning - All rights reserved
 *
 * The source code in this file is provided by the author for the sole purpose of illustrating the 
 * concepts and algorithms presented in "Scala for Machine Learning" ISBN: 978-1-783355-874-2 Packt Publishing.
 * Unless required by applicable law or agreed to in writing, software is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * Version 0.95
 */
package org.scalaml.workflow

import org.scalaml.core.design.PipeOperator
import org.scalaml.util.Display
import org.apache.log4j.Logger

	/**
	 * <p>Pre-processing module used in the workflow. This module is "injected" at run-time
	 * to build a dynamic computation workflow.</p>
	 */
trait PreprocModule[-T, +U] {
   val preProc: PipeOperator[T, U]
}

	/**
	 * <p>Processing module used in the workflow. This module is "injected" at run-time
	 * to build a dynamic computation workflow.</p>
	 */
trait ProcModule[-T, +U] {
   val proc: PipeOperator[T, U]
}

	/**
	 * <p>Post-processing module used in the workflow. This module is "injected" at run-time
	 * to build a dynamic computation workflow.</p>
	 */
trait PostprocModule[-T, +U] {
   val postProc: PipeOperator[T, U]
}




		/**
		 * <p>Generic workflow using stackable traits/modules which instances
		 * are initialize at run-time.</p>
		 */
class Workflow[T, U, V, W] {
  self: PreprocModule[T, U] with ProcModule[U, V] with PostprocModule[V, W] =>
	 
  	private val logger = Logger.getLogger("Workflow")
  	def |> (t: T): W = postProc |> (proc |> (preProc |> t))
}


// --------------------------  EOF ----------------------------------------