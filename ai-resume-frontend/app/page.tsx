"use client";

import { useState } from "react";

export default function Home() {
  const [file, setFile] = useState<File | null>(null);
  const [result, setResult] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleAnalyze = async () => {
    if (!file) {
      setError("Please select a PDF resume first.");
      return;
    }

    setLoading(true);
    setError("");
    setResult("");

    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await fetch(
        "http://localhost:8081/api/resumes/upload",
        {
          method: "POST",
          body: formData,
        }
      );

      const data = await response.text();

      if (!response.ok) {
        throw new Error(data || "Failed to analyze resume.");
      }

      setResult(data);
    } catch (err) {
      setError(
        err instanceof Error
          ? err.message
          : "Something went wrong."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="min-h-screen bg-slate-950 text-white">
      <div className="mx-auto max-w-5xl px-6 py-12">

        {/* Header */}
        <div className="mb-10 text-center">
          <p className="mb-3 text-sm font-semibold uppercase tracking-[0.3em] text-cyan-400">
            AI Powered
          </p>

          <h1 className="text-4xl font-bold tracking-tight sm:text-6xl">
            AI Resume Analyzer
          </h1>

          <p className="mx-auto mt-4 max-w-2xl text-slate-400">
            Upload your resume and analyze your skills, experience,
            education and resume score.
          </p>
        </div>

        {/* Upload Card */}
        <section className="rounded-3xl border border-slate-800 bg-slate-900 p-6 shadow-2xl sm:p-10">

          <div className="rounded-2xl border-2 border-dashed border-slate-700 bg-slate-950/50 p-8 text-center">

            <div className="mx-auto mb-5 flex h-16 w-16 items-center justify-center rounded-2xl bg-cyan-500/10 text-3xl">
              📄
            </div>

            <h2 className="text-2xl font-semibold">
              Upload Your Resume
            </h2>

            <p className="mt-2 text-sm text-slate-400">
              Select a PDF file to start the analysis.
            </p>

            <div className="mt-6">
              <label
                htmlFor="resume"
                className="inline-flex cursor-pointer rounded-xl border border-slate-700 bg-slate-800 px-5 py-3 font-medium transition hover:bg-slate-700"
              >
                Choose PDF
              </label>

              <input
                id="resume"
                type="file"
                accept=".pdf,application/pdf"
                className="hidden"
                onChange={(e) => {
                  const selectedFile = e.target.files?.[0] ?? null;
                  setFile(selectedFile);
                  setResult("");
                  setError("");
                }}
              />
            </div>

            {file && (
              <div className="mt-5 rounded-xl bg-slate-800 px-4 py-3 text-sm text-slate-300">
                Selected file:
                <span className="ml-2 font-semibold text-white">
                  {file.name}
                </span>
              </div>
            )}

            <button
              onClick={handleAnalyze}
              disabled={!file || loading}
              className="mt-6 w-full rounded-xl bg-cyan-500 px-6 py-3 font-semibold text-slate-950 transition hover:bg-cyan-400 disabled:cursor-not-allowed disabled:opacity-50 sm:w-auto"
            >
              {loading ? "Analyzing Resume..." : "Analyze Resume"}
            </button>
          </div>

          {/* Error */}
          {error && (
            <div className="mt-6 rounded-xl border border-red-500/30 bg-red-500/10 p-4 text-sm text-red-300">
              <strong>Error:</strong> {error}
            </div>
          )}

          {/* Result */}
          {result && (
            <div className="mt-8">

              <div className="mb-5 flex items-center justify-between">
                <div>
                  <p className="text-sm font-medium uppercase tracking-wider text-cyan-400">
                    Analysis Complete
                  </p>

                  <h2 className="mt-1 text-2xl font-bold">
                    Resume Analysis Result
                  </h2>
                </div>

                <div className="rounded-2xl bg-emerald-500/10 px-5 py-3 text-center">
                  <div className="text-2xl font-bold text-emerald-400">
                    ✓
                  </div>
                  <div className="text-xs text-slate-400">
                    Completed
                  </div>
                </div>
              </div>

              <div className="overflow-hidden rounded-2xl border border-slate-800 bg-slate-950">
                <div className="border-b border-slate-800 bg-slate-900 px-5 py-4">
                  <h3 className="font-semibold">
                    Resume Details
                  </h3>
                </div>

                <pre className="max-h-[600px] overflow-auto whitespace-pre-wrap p-5 text-sm leading-7 text-slate-300">
                  {result}
                </pre>
              </div>
            </div>
          )}

        </section>

        {/* Features */}
        <div className="mt-8 grid gap-4 sm:grid-cols-3">

          <div className="rounded-2xl border border-slate-800 bg-slate-900 p-5">
            <div className="mb-3 text-2xl">🔍</div>
            <h3 className="font-semibold">Skill Detection</h3>
            <p className="mt-2 text-sm text-slate-400">
              Detect technical skills from your resume.
            </p>
          </div>

          <div className="rounded-2xl border border-slate-800 bg-slate-900 p-5">
            <div className="mb-3 text-2xl">📊</div>
            <h3 className="font-semibold">Resume Score</h3>
            <p className="mt-2 text-sm text-slate-400">
              Get a score based on detected skills.
            </p>
          </div>

          <div className="rounded-2xl border border-slate-800 bg-slate-900 p-5">
            <div className="mb-3 text-2xl">💾</div>
            <h3 className="font-semibold">Database Storage</h3>
            <p className="mt-2 text-sm text-slate-400">
              Resume analysis is saved in MySQL.
            </p>
          </div>

        </div>

        <footer className="mt-10 text-center text-sm text-slate-500">
          AI Resume Analyzer • Built with Next.js & Spring Boot
        </footer>

      </div>
    </main>
  );
}