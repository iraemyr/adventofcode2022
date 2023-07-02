use criterion::{criterion_group, criterion_main, Criterion};
use day15::part2_parallel;
use std::fs;

fn bench(c: &mut Criterion) {
    let contents = fs::read_to_string("input1").expect("File not found");
    let mut group = c.benchmark_group("sample-size-example");
    // Configure Criterion.rs to detect smaller differences and increase sample size to improve
    // precision and counteract the resulting noise.
    group.significance_level(0.1).sample_size(10);
    group.bench_function("part2_parallel", |b| {
        b.iter(|| part2_parallel(contents.clone(), false))
    });
    group.finish();
}

criterion_group!(benches, bench);
criterion_main!(benches);
