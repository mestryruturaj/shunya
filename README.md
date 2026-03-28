# Shunya (शून्य)

Shunya is a high-precision, real-time sports auction engine designed to bridge the gap between casual league management and professional-grade auction dynamics.

🚀 Overview Unlike standard bidding platforms, Shunya is built specifically for the constraints of sports team building. It solves the "Bankrupt Manager" problem by enforcing a Dynamic Budget Guardrail, ensuring every participating captain maintains enough liquidity to field a full squad.

🎯 Key Features

- Atomic Bidding Engine: A hybrid real-time architecture (Spring Boot + Firebase) that eliminates race conditions among 16–32 concurrent bidders.
- Smart Budget Validation: Automated calculation of Max Allowable Bid based on remaining roster slots and base prices.
- Live Synchronized Arena: A low-latency "Auction Room" experience with sub-second price updates and automated countdown timers.
- Stateless Scalability: Designed to handle multiple concurrent auction rooms using session affinity and centralized persistence.
