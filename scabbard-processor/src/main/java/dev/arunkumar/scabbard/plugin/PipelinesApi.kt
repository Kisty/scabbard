package dev.arunkumar.scabbard.plugin

import com.github.kittinunf.result.Result
import dagger.model.BindingGraph
import dev.arunkumar.dot.DotGraph


interface Job<T, R> {
  val name: String
  fun handles(param: T): Boolean
  fun execute(param: T): Result<R, Exception>
}

interface Stage {
  val jobs: List<Job<*, *>>
}

interface Pipeline {
  val stages: List<Stage>
}

interface PipelineExecutor {
  fun execute(pipeline: Pipeline): Result<Boolean, Exception>
}

// -------------------

interface ProcessorResult

interface Processor<T : ProcessorResult> {
  val bindingGraph: BindingGraph
  fun process(): Result<T, Exception>
}

abstract class ProcessorJob<R : ProcessorResult> : Job<BindingGraph, R> {
    
}

data class DotProcessorResult(val dotGraph: DotGraph) : ProcessorResult

interface DefaultDotProcessor : Processor<DotProcessorResult>


